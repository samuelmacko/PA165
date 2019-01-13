package cz.muni.fi.pa165.blablacar.springMvc.controllers;

import cz.muni.fi.pa165.blablacar.api.dto.comment.CommentCreateDTO;
import cz.muni.fi.pa165.blablacar.api.dto.comment.CommentDTO;
import cz.muni.fi.pa165.blablacar.api.facade.CommentFacade;
import cz.muni.fi.pa165.blablacar.springMvc.security.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/comment")
public class CommentController {

    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private CommentFacade commentFacade;


    @Autowired
    private UserSession userSession;

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id, UriComponentsBuilder uriBuilder, RedirectAttributes redirectAttributes) {
        CommentDTO comment = commentFacade.getComment(id);
        log.debug("delete({})", id);
        try {
            commentFacade.deleteComment(id);
            redirectAttributes.addFlashAttribute("alert_success", "Comment \"" + comment.getId() + "\" was deleted.");
        } catch (Exception ex) {
            log.error("Comment " + id + " cannot be deleted (it is included in an order)");
            log.error(NestedExceptionUtils.getMostSpecificCause(ex).getMessage());
            redirectAttributes.addFlashAttribute("alert_danger", "Comment \"" + comment.getId() + "\" cannot be deleted.");
        }
        return "redirect:" + uriBuilder.path("/comment/list").toUriString();
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable long id, Model model) {
        log.debug("view({})", id);
        model.addAttribute("comment", commentFacade.getComment(id));
        return "comment/view";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createNewComment(@Valid @ModelAttribute("commentCreateDTO") CommentCreateDTO comment,
                                   BindingResult result,
                                   Model model,
                                   RedirectAttributes redirectAttributes,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {

        log.debug("create(formBean={})", comment);

        if (result.hasErrors()) {
            for (ObjectError ge : result.getGlobalErrors()) {
                log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : result.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.trace("FieldError: {}", fe);
            }
            model.addAttribute("commentCreateDTO", comment);
            return "comment/new";
        }
        //create
        Long id = commentFacade.createComment(comment);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Drive " + comment.getDriveId() + " was commented");
        //redirect to drive with this comment
        return "redirect:../drive/showDrive/" + comment.getDriveId();
    }


    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addCommentForm(@RequestParam(value = "driveId", required = true) Long driveId, ModelMap model){
        CommentCreateDTO newComment = new CommentCreateDTO();
        newComment.setDriveId(driveId);
        newComment.setAuthorId(userSession.getUserId());
        model.addAttribute("commentCreateDTO", newComment);
        return "comment/new";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<CommentDTO> list = commentFacade.getAllComments();
        log.debug("list({})", list);

        model.addAttribute("comments", list);
        return "comment/list";
    }


    @ModelAttribute(name = "userSession")
    public UserSession addUserSession(){
        return userSession;
    }
}
